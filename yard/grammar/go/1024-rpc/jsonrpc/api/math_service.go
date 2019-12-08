package api

import (
	"errors"
	"fmt"
	"reflect"
	"runtime"
)

type MathArgs struct {
	A, B     int
	Operator string
}

type MathService struct {
}

func myAdd(lhs, rhs int) int {
	return lhs + rhs
}

func mySub(lhs, rhs int) int {
	return lhs - rhs
}

func apply(op func(int, int) int, a, b int) int {
	p := reflect.ValueOf(op).Pointer()
	opName := runtime.FuncForPC(p).Name()
	fmt.Printf("Calling function %s with args "+
		"(%d, %d)\n", opName, a, b)
	return op(a, b)
}

func (MathService) Eval(args MathArgs, result *float64) error {
	if args.B == 0 && args.Operator == "/" {
		return errors.New("division by zero")
	}

	var err error = nil
	a, b := args.A, args.B
	switch args.Operator {
	case "+":
		*result = float64(a + b)
	case "-":
		*result = float64(a - b)
	case "*":
		*result = float64(a * b)
	case "/":
		*result = float64(a / b)
	default:
		*result = 0
		err = errors.New("Invalid Operator" + args.Operator)
	}
	return err
}
