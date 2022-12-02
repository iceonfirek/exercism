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
    match _color {
        ResistorColor::Black => 0,
        ResistorColor::Brown => 1,
        ResistorColor::Red => 2,
        ResistorColor::Orange => 3,
        ResistorColor::Yellow => 4,
        ResistorColor::Green => 5,
        ResistorColor::Blue => 6,
        ResistorColor::Violet => 7,
        ResistorColor::Grey => 8,
        ResistorColor::White => 9,
    }
}

pub fn value_to_color_string(value: u32) -> String {
    unimplemented!(
        "convert the value {} into a string representation of color",
        value
    )
}

pub fn colors() -> Vec<ResistorColor> {
    unimplemented!("return a list of all the colors ordered by resistance")
}

fn main() {
    println!("{}", color_to_value (ResistorColor::White));
}

