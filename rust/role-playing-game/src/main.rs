use role_playing_game::*;

fn main() {
    let mut bob = Player {
        health: 1,
        mana: Some(10),
        level: 12,
    };
    println!("{:?} \n {:?}", bob.cast_spell (15), bob);
}
