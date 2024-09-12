package main

import "fmt"

func main() {
	slice := []uint8{1, 34, 4, 3, 1}
	fmt.Println(slice)

	newSlice := slice
	newSlice[3] = 44
	//? Slices use pointers that changes the original value as well
	fmt.Println(slice)
	fmt.Println(newSlice)

	/*
		* without pointers
		thing1 := [5]float64{1, 2, 3, 4, 5}
		fmt.Printf("\nThe memory location of thing1 array is: %p", &thing1)

		result := square(thing1)
		fmt.Printf("\nThe result is %v", result)
		fmt.Printf("\nThe value of thing1 is %v", thing1)
	*/

	thing1 := [5]float64{1, 2, 3, 4, 5}
	fmt.Printf("\nThe memory location of thing1 array is: %p", &thing1)

	result := square(&thing1)
	fmt.Printf("\nThe result is %v", result)

	//? The values of original slice changed after function call
	fmt.Printf("\nThe value of thing1 is %v", thing1)
}

/*
* without pointers

	func square(thing2 [5]float64) [5]float64 {
		fmt.Printf("\nThe memory location of thing2 array is: %p", &thing2)

		for i, _ := range thing2 {
			thing2[i] = thing2[i] * thing2[i]
		}
		return thing2
	}
*/

func square(thing2 *[5]float64) [5]float64 {
	fmt.Printf("\nThe memory location of thing2 array is: %p", thing2)

	for i, _ := range thing2 {
		thing2[i] = thing2[i] * thing2[i]
	}
	return *thing2
}
