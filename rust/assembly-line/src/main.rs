// This stub file contains items that aren't used yet; feel free to remove this module attribute
// to enable stricter warnings.
#![allow(unused)]
use assembly_line::production_rate_per_hour;
use assembly_line::working_items_per_minute;

fn main() {
    let z = 8;
    let x = working_items_per_minute(z);
    let y = production_rate_per_hour(z);
    println!("per_minute:{}\nper_hour:{}\nspeed_is:{}", x, y, z);
}
