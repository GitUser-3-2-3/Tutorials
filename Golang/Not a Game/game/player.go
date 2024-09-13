package main

type Player struct {
	pos   Position
	level *Level
}

func (player *Player) update() {
	player.pos.x += 1
	if player.pos.x == player.level.width-1 {
		player.pos.x -= 1
	}
}
