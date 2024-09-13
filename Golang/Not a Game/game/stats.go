package main

import "time"

//goland:noinspection GoSnakeCaseUsage
const MAX_SAMPLES = 100

type Stats struct {
	start  time.Time
	frames int
	fps    float64
}

func newStats() *Stats {
	return &Stats{
		fps:   69,
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
