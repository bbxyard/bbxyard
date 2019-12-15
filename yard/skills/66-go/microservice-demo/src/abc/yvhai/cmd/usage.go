package cmd

import (
	"flag"
	"fmt"
	"os"
	"text/tabwriter"
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
