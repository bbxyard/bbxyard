/*
Copyright © 2019 boxu@yvhai.com

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package cmd

import (
	"fmt"

	"github.com/spf13/cobra"
)

var (
	srcDir    string = "./"
	destDir   string = "/tmp"
	hostname  string = "localhost"
	debugMode bool   = false
	hosts     []string
	port      uint16 = 36036
)

// pushCmd represents the push command
var pushCmd = &cobra.Command{
	Use:   "push",
	Short: "A brief description of your command",
	Long: `A longer description that spans multiple lines and likely contains examples
and usage of using your command. For example:

Cobra is a CLI library for Go that empowers applications.
This application is a tool to generate the needed files
to quickly create a Cobra application.`,
	Run: func(cmd *cobra.Command, args []string) {
		fmt.Printf("push called %v\n", args)
		fmt.Println("=== Args ===")
		fmt.Printf("  ==> src-dir: %s\n", srcDir)
		fmt.Printf("  ==> dest-dir: %s\n", destDir)
		fmt.Printf("  ==> hostname: %s\n", hostname)
		fmt.Printf("  ==> debug: %v\n", debugMode)
		fmt.Printf("  ==> hosts: %v, %d\n", hosts, len(hosts))
		fmt.Printf("  ==> port: %d\n", port)
		fmt.Printf("  ==> cfg-file: %s\n", cfgFile)
		fmt.Printf("  ==> author: %s\n", author)
	},
}

func init() {
	rootCmd.AddCommand(pushCmd)

	pushCmd.Flags().StringVarP(&srcDir, "src-dir", "i", srcDir, "src/input dir")
	pushCmd.Flags().StringVarP(&destDir, "dest-dir", "o", destDir, "dest/output dir")
	pushCmd.Flags().StringVar(&hostname, "hostname", hostname, "dest hostname")
	pushCmd.Flags().StringArrayVar(&hosts, "hosts", hosts, "dest hosts")
	pushCmd.Flags().BoolVarP(&debugMode, "debugMode", "D", debugMode, "show debugMode info")
	pushCmd.Flags().Uint16VarP(&port, "port", "p", port, "host port")
	pushCmd.MarkFlagRequired("src-dir")
}
