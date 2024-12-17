package main

import "fmt"

func add(x, y int) int {
	return x + y
}

func swap(x, y string) (string, string) {
	return y, x
}

func split(sum int) (x, y int) {
	x = sum * 4 / 9
	y = sum - x
	return
}

func main() {
	fmt.Print(add(4, 1))

	a, b := swap("Srivastav", "Parth ")
	fmt.Print("\n", a, b)

	fmt.Println()
	fmt.Print(split(20))
}
