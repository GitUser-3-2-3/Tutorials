package main

import (
	"errors"
	"fmt"
	"io"
	"log"
	"os"
	"strconv"
)

func addition(i int, j int) (int, error) { return i + j, nil }

func subtraction(i int, j int) (int, error) { return i - j, nil }

func multiplication(i int, j int) (int, error) { return i * j, nil }

func division(i int, j int) (int, error) {
	if j == 0 {
		return 0, errors.New("cannot divide by zero")
	}
	return i / j, nil
}

var opMapping = map[string]func(int, int) (int, error){
	"+": addition,
	"-": subtraction,
	"*": multiplication,
	"/": division,
}

func calculate() {
	expressions := [][]string{
		{"2", "+", "3"},
		{"2", "-", "3"},
		{"2", "*", "3"},
		{"2", "/", "0"},
	}
	for _, expression := range expressions {
		if len(expression) != 3 {
			fmt.Println("invalid expression:", expression)
			continue
		}
		p1, err := strconv.Atoi(expression[0])
		if err != nil {
			fmt.Println(err)
			continue
		}
		op := expression[1]
		opFunc, ok := opMapping[op]
		if !ok {
			fmt.Println("unsupported operator:", op)
			continue
		}
		p2, err := strconv.Atoi(expression[2])
		if err != nil {
			fmt.Println(err)
			continue
		}
		result, err := opFunc(p1, p2)
		if err != nil {
			log.Fatal(err)
		}
		fmt.Println(result)
	}
}

func fileLen(fileName string) (int, error) {
	f, err := os.Open(fileName)
	if err != nil {
		return 0, err
	}
	f.Close()
	data := make([]byte, 2048)
	total := 0

	for {
		count, err := f.Read(data)
		total += count
		if err != nil {
			if err != io.EOF {
				return 0, err
			}
			break
		}
	}
	fmt.Println("file len executed")
	return total, err
}

func fileLenCall() {
	count, err := fileLen(os.Args[1])
	if err != nil {
		log.Fatal(err)
	}

	fmt.Println(count)
}

func prefixerHelper(prefix string) func(result string) string {
	return func(result string) string {
		return prefix + " " + result
	}
}

func prefixer() {
	helloFixer := prefixerHelper("Hello")
	fmt.Println(helloFixer("Parth"))
	fmt.Println(helloFixer("Juhi"))
}
