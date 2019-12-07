package cmd

import (
	"fmt"

	"github.com/spf13/cobra"
)

func init() {
	rootCmd.AddCommand(hugoCmd)
}

var hugoCmd = &cobra.Command{
	Use:   "hugo",
	Short: "这是Hugo说明文字",
	Long:  `All software has versions. This is Hugo's`,
	// PersistentPreRun: func(cmd *cobra.Command, args []string) {
	// 	fmt.Printf("Inside hugoCmd PersistentPreRun with args: %v\n", args)
	// },
	PreRun: func(cmd *cobra.Command, args []string) {
		fmt.Printf("Inside hugoCmd PreRun with args: %v\n", args)
	},
	PostRun: func(cmd *cobra.Command, args []string) {
		fmt.Printf("Inside hugoCmd PostRun with args: %v\n", args)
	},
	PersistentPostRun: func(cmd *cobra.Command, args []string) {
		fmt.Printf("Inside hugoCmd PersistentPostRun with args: %v\n", args)
	},
	Run: func(cmd *cobra.Command, args []string) {
		fmt.Println("Hugo Static Site Generator v0.9 -- HEAD")
	},
}
