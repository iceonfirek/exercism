#[derive(Debug, PartialEq, Eq)]
pub enum ResistorColor {
    Black,
    Blue,
    Brown,
    Green,
    Grey,
    Orange,
    Red,
    Violet,
    White,
    Yellow,
}

pub fn color_to_value(_color: ResistorColor) -> u32 {
    int_enum Type {
	
    }

}

pub fn value_to_color_string(value: u32) -> String {
    

}

pub fn colors() -> Vec<ResistorColor> {
    unimplemented!("return a list of all the colors ordered by resistance")
}

fn main() {
    println!("{}", color_to_value(ResistorColor::White));
}
