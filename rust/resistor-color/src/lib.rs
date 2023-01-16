use enum_iterator::*;
use int_enum::IntEnum;

#[repr(u32)]
#[derive(Debug, PartialEq, Eq, IntEnum, Clone, Copy, Sequence, PartialOrd, Ord)]

pub enum ResistorColor {
    Black = 0,
    Blue = 6,
    Brown = 1,
    Green = 5,
    Grey = 8,
    Orange = 3,
    Red = 2,
    Violet = 7,
    White = 9,
    Yellow = 4,
}

pub fn color_to_value(_color: ResistorColor) -> u32 {
    _color as u32
}

pub fn value_to_color_string(_value: u32) -> String {
    match ResistorColor::from_int(_value) {
        Ok(value) => format!("{:?}", value),
        Err(_) => "value out of range".to_string(),
    }
}

pub fn colors() -> Vec<ResistorColor> {
    let mut c :Vec<ResistorColor>= all::<ResistorColor>().collect();
    c.sort();
    c
}
