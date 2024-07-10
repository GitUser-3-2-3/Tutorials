package main

import (
	"fmt"
	"io"
	"log"
	"os"
)

func deferExample() int {
	a := 10
	defer func(val int) {
		fmt.Println("first: ", val)
	}(a)

	a = 20
	defer func(val int) {
		fmt.Println("second: ", val)
	}(a)

	a = 30
	defer func(val int) {
		fmt.Println("third: ", val)
	}(a)

	a = 40
	fmt.Println("exiting: ", a)
	return a
}

func catProgram() {
	if len(os.Args) < 2 {
		log.Fatal("no file specified")
	}

	f, err := os.Open(os.Args[1])
	if err != nil {
		log.Fatal(err)
	}
	defer func(f *os.File) {
		err := f.Close()
		if err != nil {
			log.Fatal(err)
		}
	}(f)

	data := make([]byte, 2048)
	for {
		count, err := f.Read(data)

		_, wErr := os.Stdout.Write(data[:count])
		if wErr != nil {
			log.Fatal(wErr)
		}

		if err != nil {
			if err != io.EOF {
				log.Fatal(err)
			}
			break
		}
	}
}
