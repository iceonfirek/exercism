// This stub file contains items that aren't used yet; feel free to remove this module attribute
// to enable stricter warnings.
#![allow(unused)]

pub fn can_construct_note(magazine: &[&str], note: &[&str]) -> bool {
    let mut magazine_copy = magazine.clone().to_vec();
    let mut match_count = 0;
    for i in 0..note.len() {
        for j in 0..magazine_copy.len() {
            if note.get(i) == magazine_copy.get(j) {
                match_count += 1;
                magazine_copy.remove(j);
                break;
            }
        }
    }
    match_count == note.len()
}


//Hash Map method

// use std::collections::HashMap;
// pub fn can_construct_note(magazine: &[&str], note: &[&str]) -> bool {
//     let mut avail = HashMap::new();
//     for word in magazine {
//         *avail.entry(word).or_insert(0) += 1;
//     }
//     for word in note {
//         *avail.entry(word).or_insert(0) -= 1;
//     }
//     avail.values().all(|&x| x >= 0)
// }
