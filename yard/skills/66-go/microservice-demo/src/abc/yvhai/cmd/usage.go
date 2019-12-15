package cmd

import (
	"flag"
	"fmt"
	"os"
	"os/signal"
	"syscall"
	"text/tabwriter"

	"github.com/oklog/oklog/pkg/group"
)

func UsageFor(fs *flag.FlagSet, short string) func() {
	return func() {
		var fp = os.Stderr
		fmt.Fprintf(fp, "USAGE\n")
		fmt.Fprintf(fp, "  %s\n", short)
		fmt.Fprintf(fp, "\n")
		fmt.Fprintf(fp, "FLAGS\n")
		w := tabwriter.NewWriter(fp, 0, 2, 2, ' ', 0)
		fs.VisitAll(func(f *flag.Flag) {
			fmt.Fprintf(w, "\t-%s %s\t%s\n", f.Name, f.DefValue, f.Usage)
		})
		w.Flush()
		fmt.Fprintf(fp, "\n")
	}
}

// AddSystemSignalHook just sits and waits for ctrl-C
func AddSystemSignalHook(g group.Group) {
	cancelInterrupt := make(chan struct{})
	g.Add(func() error {
		c := make(chan os.Signal, 1)
		signal.Notify(c, syscall.SIGINT, syscall.SIGTERM)
		select {
		case sig := <-c:
			return fmt.Errorf("received signal %s", sig)
		case <-cancelInterrupt:
			return nil
		}
	}, func(err error) {
		close(cancelInterrupt)
	})
}
