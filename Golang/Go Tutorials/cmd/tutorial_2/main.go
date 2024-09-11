package main

import "fmt"

func main() {
	var intNum int16 = 24958
	fmt.Println(intNum)

	var floatNum float64 = 12345678.9
	fmt.Println(floatNum)

	var float32Num float32 = 23242.3
	var int16Num int16 = 2342
	var sumOfThem = float32Num + (float32(int16Num))
	fmt.Println(sumOfThem)

	fmt.Println(len("string"))
	var myRune rune = 's'
	fmt.Println(myRune)

	var myText = "string"
	fmt.Println(myText)

	//	myVar := foo() // not good practice
	//	var myVar string  = foo()
	//	fmt.Println(myVar)

	const myConst string = "this cannot be changed"
	fmt.Println(myConst)
	// myConst = "cannot be changed"
}
