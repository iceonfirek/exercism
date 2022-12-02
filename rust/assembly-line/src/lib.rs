// This stub file contains items that aren't used yet; feel free to remove this module attribute
// to enable stricter warnings.
#![allow(unused)]

pub fn production_rate_per_hour(speed: u8) -> f64 {
    if speed < 5 {
        (speed as f64) * 221.0
    } else if speed < 9 {
        (speed as f64) * 0.9 * 211.0
    } else {
        (speed as f64) * 0.77 * 211.0
    }
}

pub fn working_items_per_minute(speed: u8) -> u32 {
    (production_rate_per_hour(speed) / 60.0) as u32
}

assembly_line::working_items_per_minute(6)
