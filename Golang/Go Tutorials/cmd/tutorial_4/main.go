package main

import "fmt"

func main() {
	intArr := [...]int{2, 32, 4}
	fmt.Println(intArr)

	intSlice := []int{2, 24, 5}
	fmt.Printf("The length of the slice is %v and capacity is %v ", len(intSlice), cap(intSlice))

	intSlice = append(intSlice, 4)
	fmt.Printf("\nThe length of the slice is %v and capacity is %v ", len(intSlice), cap(intSlice))

	anotherSlice := []int{33, 21, 22}
	intSlice = append(intSlice, anotherSlice...)
	fmt.Printf("\nThe length of the slice is %v and capacity is %v ", len(intSlice), cap(intSlice))

	efficientSlice := make([]int32, 3, 10)
	fmt.Println(efficientSlice)

	myMap := make(map[string]uint8)
	fmt.Println(myMap)

	myMap2 := map[string]uint8{"parth": 21, "juhi": 22}
	fmt.Println(myMap2)

	age, ok := myMap2["juhi"]
	if ok {
		fmt.Printf("The age is %v", age)
	} else {
		fmt.Println("Invalid name")
	}

	for name, age := range myMap2 {
		fmt.Printf("\nName: %v, Age: %v", name, age)
	}
}
