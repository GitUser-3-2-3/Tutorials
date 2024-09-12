package main

import (
	"bytes"
	"fmt"
	"os"
)

//goland:noinspection GoUnusedConst
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
		for w := 0; w < width; w++ {
			data[h] = make([]byte, width)
		}
	}
	return &Level{
		width:  width,
		height: height,
		data:   data,
	}
}

// todo -> potential bug! check for merger with newLevel before being used in renderWalls
func setWalls(level *Level) {
	for h := 0; h < level.height; h++ {
		for w := 0; w < level.width; w++ {
			if h == 0 || h == level.height-1 || w == 0 || w == level.width-1 {
				level.data[h][w] = WALL
			}
		}
	}
}

type Game struct {
	isRunning bool
	level     *Level
	drawBuf   *bytes.Buffer
}

func newGame(width, height int) *Game {
	lvl := newLevel(width, height)
	return &Game{
		level:   lvl,
		drawBuf: new(bytes.Buffer),
	}
}

func (game *Game) start() {
	game.isRunning = true
	game.loop()
}

func (game *Game) loop() {
	index := 0
	for game.isRunning {
		game.update()
		game.render()
		if index == 10 {
			break
		}
		index++
	}
}

func (game *Game) update() {
	// update
}

func (game *Game) renderWalls() {
	var height = game.level.height
	var width = game.level.width

	for h := 0; h < height; h++ {
		for w := 0; w < width; w++ {

			var data = game.level.data[h][w]

			if data == NOTHING {
				game.drawBuf.WriteString(" ")
			}
			if data == WALL {
				game.drawBuf.WriteString("▣")
			}
		}
		game.drawBuf.WriteString("\n")
	}
}

func (game *Game) render() {
	game.renderWalls()
	if _, err := fmt.Fprint(os.Stdout, game.drawBuf.String()); err != nil {
		fmt.Printf("ERROR in 'render': %v", err)
	}
}

func main() {
	width := 90
	height := 15

	game := newGame(width, height)
	setWalls(game.level)
	game.start()
}
