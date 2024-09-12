package main

import (
	"fmt"
	"os"
	"strings"
	"time"
)

//goland:noinspection GoSnakeCaseUsage, GoUnusedConst
const (
	NOTHING = 0
	WALL    = 1
	PLAYER  = 69

	MAX_SAMPLES = 100
)

type Stats struct {
	start  time.Time
	frames int
	fps    float64
}

func newStats() *Stats {
	return &Stats{
		start: time.Now(),
	}
}

func (stats *Stats) update() {
	stats.frames++
	if stats.frames == MAX_SAMPLES {
		stats.fps = float64(stats.frames) / time.Since(stats.start).Seconds()
		stats.frames = 0
		stats.start = time.Now()
	}
}

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

type Game struct {
	isRunning bool
	level     *Level
	stats     *Stats
	drawBuf   strings.Builder
}

func newGame(width, height int) *Game {
	lvl := newLevel(width, height)
	return &Game{
		level: lvl,
		stats: newStats(),
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

func (game *Game) renderStats() {
	game.drawBuf.WriteString("--STATS\n")
	game.drawBuf.WriteString(fmt.Sprintf("FPS: %.2f", game.stats.fps))
}

func (game *Game) render() {
	game.drawBuf.Reset()
	if _, err := fmt.Fprint(os.Stdout, "\033[2J\033[1;1H"); err != nil {
		fmt.Printf("ERROR in 'render': %v", err)
	}
	game.renderWalls()
	game.renderStats()
	if _, err := fmt.Fprint(os.Stdout, game.drawBuf.String()); err != nil {
		fmt.Printf("ERROR in 'render': %v", err)
	}
}

func main() {
	width := 90
	height := 15

	game := newGame(width, height)
	game.start()
}
