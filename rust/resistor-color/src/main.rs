use resistor_color::*;

fn main() {
    println!(
        "int color is {}, \ncolor is {}, \nAll colors are {:?}",
        value_to_color_string(9),
        color_to_value(ResistorColor::Green),
        colors()
    )
}
