package main

const (
	NOTHING = 0
	WALL    = 1
	PLAYER  = 69
)

type Level struct {
	width, height int
	data          [][]int
}

func (level *Level) set(pos Position, val int) {
	level.data[pos.y][pos.x] = val
}

func newLevel(width, height int) *Level {
	data := make([][]int, height)
	for h := 0; h < height; h++ {
		data[h] = make([]int, width)
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
