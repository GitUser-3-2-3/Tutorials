package main

import (
	"bytes"
	"fmt"
	"os"
)

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

// todo -> potential bug! check for merger with newLevel before being used in renderLevel
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
	for game.isRunning {
		game.update()
		game.render()
	}
}

func (game *Game) update() {
	// update
}

func (game *Game) renderLevel() {
	setWalls(game.level)

	for h := 0; h < game.level.height; h++ {
		for w := 0; w < game.level.width; w++ {

			if game.level.data[h][w] == NOTHING {
				game.drawBuf.WriteString(" ")
			} else if game.level.data[h][w] == WALL {
				game.drawBuf.WriteString("▣")
			}
		}
		game.drawBuf.WriteString("\n")
	}
}

func (game *Game) render() {
	game.renderLevel()
	if _, err := fmt.Fprint(os.Stdout, game.drawBuf.String()); err != nil {
		fmt.Printf("ERROR in 'render': %v", err)
	}
}

func main() {
	width := 80
	height := 15
}
