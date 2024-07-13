package main

import "fmt"

func main() {
	println()

	s := make([]string, 0, 10)
	str := "string"
	s = append(s, "this", "is", "first", "slice")
	fmt.Println(s)

	updateSlice(s, str)
	fmt.Println(s)

	growSlice(s, str)
	fmt.Println(s)

	slice := []string{"this", "is", "slice"}
	str2 := "string"
	fmt.Println(slice)

	updateSlice(slice, str2)
	fmt.Println(slice)

	growSlice(slice, str2)
	fmt.Println(slice)
}
