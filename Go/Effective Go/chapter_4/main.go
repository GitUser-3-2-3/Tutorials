package main

import (
	"errors"
	"fmt"
	"os"
)

type MyFuncOpts struct {
	firstname string
	lastname  string
	age       int
}

func main() {
	println()

	result, remainder, err := divAndRemainder(23, 3)
	if err != nil {
		fmt.Println(err)
		os.Exit(1)
	}
	fmt.Println(result, remainder)
}

func addToBase(base int, vals ...int) []int {
	out := make([]int, 0, len(vals))
	for _, val := range vals {
		out = append(out, base+val)
	}
	return out
}

func divAndRemainder(num, denom int) (result int, remainder int, err error) {
	if denom == 0 {
		err = errors.New("cannot divide by zero")
		return 0, 0, err
	}
	result, remainder = num/denom, num%denom
	return result, remainder, err
}
