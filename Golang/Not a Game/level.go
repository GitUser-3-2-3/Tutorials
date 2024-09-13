package main

const (
	NOTHING = 0
	WALL    = 1
	PLAYER  = 69
)

type Level struct {
	width, height int
	data          [][]byte
}

func newLevel(width, height int) *Level {
	data := make([][]byte, height)
	for h := 0; h < height; h++ {
		data[h] = make([]byte, width)
		for w := 0; w < width; w++ {
			if h == 0 || h == height-1 || w == 0 || w == width-1 {
				data[h][w] = WALL
			}
		}
	}
	return &Level{
		width:  width,
		height: height,
		data:   data,
	}
}
