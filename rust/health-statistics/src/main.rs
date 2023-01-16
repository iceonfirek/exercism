use health_statistics::*;

fn main() {
    let mut bob = User::new (String::from("Bob"), 32, 185.5);
    bob.set_weight(190.0);
    bob.set_age (28);
    println!("{:?}\n{:?}", bob, bob.weight())
}
