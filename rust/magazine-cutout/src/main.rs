use magazine_cutout::*;
use std::collections::HashMap;


fn main() {
    
let mut magazine = "enough is enough when enough is Enough"
        .split_whitespace()
        .collect::<Vec<&str>>();
let note = "Enough is enough"
        .split_whitespace()
        .collect::<Vec<&str>>();


    let mag_count = &magazine.len ();
    let note_count = &note.len ();

//    let a = can_construct_note(&magazine, &note);

    // let mut match_count = 0;
    // for i in 0..note.len() {
    // 	for j in 0..magazine.len() {
    // 	    if note.get(i) == magazine.get(j) {
    // 		match_count += 1;
    // 		magazine.remove (j);
    // 		break
    // 	    }
    // 	}
    // }

    let mut avail = HashMap::new();
    for word in &magazine {
        *avail.entry(word).or_insert(0) += 1;
    }
    for word in &note {
        *avail.entry(word).or_insert(0) -= 1;
    }
    let a = avail.values().all(|&x| x >= 0);
	
    println!(
	     "magazine:{:?}\nmagezine_count:{:?}\nnote:{:?}\nnote_count:{:?}\n{:?}\n{:?}",
	     magazine, mag_count, note, note_count, avail, a
	     )
}
