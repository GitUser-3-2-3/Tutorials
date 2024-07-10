package main

import (
	"errors"
	"fmt"
)

type MyFuncOpts struct {
	firstname string
	lastname  string
	age       int
}

func main() {
	println()

	multConsume()
}

func makeMult(base int) func(int) int {
	return func(factor int) int {
		return base * factor
	}
}

func multConsume() {
	twoBase := makeMult(2)
	threeBase := makeMult(3)

	for i := 0; i < 3; i++ {
		fmt.Println(twoBase(i), threeBase(i))
	}
}

func anon() {
	f := func(j int) {
		fmt.Println("printing ", j, " from inside the function")
	}

	for i := 0; i < 5; i++ {
		f(i)
	}

	for i := 0; i < 5; i++ {
		func(j int) {
			fmt.Println("printing ", j, " from anon func")
		}(i)
	}
}

func f1(a string) int {
	return len(a)
}

func f2(a string) int {
	total := 0
	for _, v := range a {
		fmt.Println(v)
		total += int(v)
	}

	return total
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
