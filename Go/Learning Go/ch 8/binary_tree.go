package main

type OrderableFunc[T any] func(t1, t2 T) int

type Tree[T any] struct {
	orderFn OrderableFunc[T]
	root    *Node[T]
}

type Node[T any] struct {
	val         T
	left, right *Node[T]
}

func NewTree[T any](orderFn OrderableFunc[T]) *Tree[T] {
	return &Tree[T]{
		orderFn: orderFn,
	}
}


