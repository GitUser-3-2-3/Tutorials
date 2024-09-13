package main

type Player struct {
	pos     Position
	level   *Level
	reverse bool
}

func (player *Player) update() {
	if player.reverse {
		player.pos.x -= 1

		if player.pos.x == 0 {
			player.reverse = false
		}
	}

	if !player.reverse {
		player.pos.x += 1

		if player.pos.x == player.level.width-1 {
			player.pos.x -= 1
			player.reverse = true
		}
	}
}
