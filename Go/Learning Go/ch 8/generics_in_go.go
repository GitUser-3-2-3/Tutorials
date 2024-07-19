package main

import "fmt"

// Map turns a []T1 to a []T2 using a mapping function
// This function has two type parameters, T1 and T2
// This works with slices of any type.
func myMap[T1, T2 any](slice []T1, fn func(T1) T2) []T2 {
	r := make([]T2, len(slice))
	for i, val := range slice {
		r[i] = fn(val)
	}
	return r
}

// Reduce reduces a []T1 to a single value using a reduction function
func myReduce[T1, T2 any](slice []T1, initializer T2, fn func(T2, T1) T2) T2 {
	r := initializer
	for _, val := range slice {
		r = fn(r, val)
	}
	return r
}

// Filter filters values from a slice using a filter function.
// It only returns new slice with only the elements of 'slice'
// for which fn returned true
func myFilter[T any](slice []T, fn func(T) bool) []T {
	var r []T
	for _, val := range slice {
		if fn(val) {
			r = append(r, val)
		}
	}
	return r
}

func mainOne() {
	words := []string{"One", "Potato", "Two", "Potato"}
	filtered := myFilter(words, func(slice string) bool {
		return slice != "Potato"
	})
	fmt.Println(filtered)

	lengths := myMap(filtered, func(str string) int {
		return len(str)
	})
	fmt.Println(lengths)

	sum := myReduce(lengths, 0, func(acc int, val int) int {
		return acc + val
	})
	fmt.Println(sum)
}
