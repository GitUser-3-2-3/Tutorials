package main

import (
	"fmt"
	"os"
	"strings"
	"time"
)

type Game struct {
	isRunning bool
	level     *Level
	stats     *Stats
	player    *Player
	drawBuf   strings.Builder
}

func newGame(width, height int) *Game {
	lvl := newLevel(width, height)
	return &Game{
		level: lvl,
		stats: newStats(),
		player: &Player{
			pos: Position{
				x: 2, y: 5,
			},
			level: lvl,
		},
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
		game.stats.update()
		time.Sleep(time.Millisecond * 15)
	}
}

func (game *Game) update() {
	game.level.set(game.player.pos, NOTHING)
	game.player.update()
	game.level.set(game.player.pos, PLAYER)
}

func (game *Game) renderLevels() {

	var height = game.level.height
	var width = game.level.width

	for h := 0; h < height; h++ {
		for w := 0; w < width; w++ {
			var data = game.level.data[h][w]

			if data == NOTHING {
				game.drawBuf.WriteString(" ")
			}
			if data == WALL {
				game.drawBuf.WriteString("⑆")
			}
			if data == PLAYER {
				game.drawBuf.WriteString("☃")
			}
		}
		game.drawBuf.WriteString("\n")
	}
}

func (game *Game) renderStats() {
	game.drawBuf.WriteString("--STATS\n")
	game.drawBuf.WriteString(fmt.Sprintf("FPS: %.2f\n", game.stats.fps))
}

func (game *Game) render() {
	game.drawBuf.Reset()
	if _, err := fmt.Fprint(os.Stdout, "\033[2J\033[1;1H"); err != nil {
		fmt.Printf("ERROR in 'render': %v", err)
	}
	game.renderLevels()
	game.renderStats()
	// learn how Fprint works
	if _, err := fmt.Fprint(os.Stdout, game.drawBuf.String()); err != nil {
		fmt.Printf("ERROR in 'render': %v", err)
	}
}
