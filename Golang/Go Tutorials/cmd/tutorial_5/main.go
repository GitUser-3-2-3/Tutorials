package main

import (
	"fmt"
	"strings"
)

func main() {
	var myString = []rune("résumé")
	stringIndexed := myString[1]
	fmt.Printf("%v, %T\n", stringIndexed, stringIndexed)

	for index, value := range myString {
		fmt.Println(index, value)
	}

	/*
		inefficient way of concatenating strings
			strSlice := []string{"s", "u", "m", "m", "e", "r"}
			var concatStr = ""
			for i, _ := range strSlice {
				concatStr += strSlice[i]
			}
			fmt.Printf("%v", concatStr)
	*/

	// efficient way of concatenating strings
	strSlice := []string{"s", "u", "m", "m", "e", "r"}
	var stringBuilder strings.Builder

	for index, _ := range strSlice {
		stringBuilder.WriteString(strSlice[index])
	}
	concatStr := stringBuilder.String()
	fmt.Println(concatStr)
}
