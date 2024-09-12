package main

import (
	"bytes"
	"fmt"
)

const (
	NOTHING = 0
	WALL    = 1
	PLAYER  = 69
)

type Game struct {
}

func (game *Game) update() {
	// update
}

func (game *Game) render() {
	// render
}

func initializeLevel(width, height int) [][]byte {
	level := make([][]byte, height)

	for h := 0; h < height; h++ {
		for w := 0; w < width; w++ {
			level[h] = make([]byte, width)
		}
	}
	return level
}

func setWalls(level [][]byte, width, height int) {
	for h := 0; h < height; h++ {
		for w := 0; w < width; w++ {
			if h == 0 || h == height-1 || w == 0 || w == width-1 {
				level[h][w] = WALL
			}
		}
	}
}

func renderWalls(level [][]byte, width, height int) string {
	buf := new(bytes.Buffer)

	for h := 0; h < height; h++ {
		for w := 0; w < width; w++ {
			if level[h][w] == NOTHING {
				buf.WriteString(" ")
			}
			if level[h][w] == WALL {
				buf.WriteString("H")
			}
		}
		buf.WriteString("\n")
	}
	return buf.String()
}

func main() {
	fmt.Println()
	width := 80
	height := 15

	level := initializeLevel(width, height)
	setWalls(level, width, height)
	fmt.Println(renderWalls(level, width, height))
}
