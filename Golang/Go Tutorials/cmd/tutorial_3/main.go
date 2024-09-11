package main

import (
	"errors"
	"fmt"
)

func main() {
	num := 32
	denom := 44

	var result, remainder, err = intDivision(num, denom)

	/*
		if err != nil {
			fmt.Println(err.Error())
		} else if remainder == 0 {
			fmt.Printf("The result of the div is %v ", result)
		} else {
			fmt.Printf("The result of the div is %v and remainder is %v", result, remainder)
		}
	*/

	switch {
	case err != nil:
		fmt.Printf(err.Error())
	case remainder == 0:
		fmt.Printf("The result of the div is %v ", result)
	default:
		fmt.Printf("The result of the div is %v and remainder is %v", result, remainder)
	}
}

func HelloWorld(data string) {
	fmt.Println(data)
}

func intDivision(num, denom int) (int, int, error) {
	var err error
	if denom == 0 {
		err = errors.New("cannot divide by zero")
		return 0, 0, err
	}

	var result = num / denom
	var remainder = num % denom

	return result, remainder, err
}
