/// Create an empty vector
pub fn create_empty() -> Vec<u8> {
    vec![]
}

/// Create a buffer of `count` zeroes.
///
/// Applications often use buffers when serializing data to send over the network.
pub fn create_buffer(count: usize) -> Vec<u8> {
    vec![0; count]
}

/// Create a vector containing the first five elements of the Fibonacci sequence.
///
/// Fibonacci's sequence is the list of numbers where the next number is a sum of the previous two.
/// Its first five elements are `1, 1, 2, 3, 5`.

pub fn fibonacci() -> Vec<u8> {
    let mut fib = vec![0; 5];
    fib[0] = 1;
    fib[1] = 1;
    for i in 2..5 {
        fib[i] = fib[i - 1] + fib[i - 2];
    }
    fib
}
// pub fn fibonacci() -> Vec<u8> {
//     let mut a = 0;
//     let mut b = 1;
//     let mut fib = vec![];
//     for _i in 2..5 {
//         fib.push(a);
//         fib.push(b);
//         a += b;
//         b += a;
//     }
//     fib.remove(0);
//     fib
// }
