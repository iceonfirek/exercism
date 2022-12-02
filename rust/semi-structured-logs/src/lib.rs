// This stub file contains items that aren't used yet; feel free to remove this module attribute
// to enable stricter warnings.
#![allow(unused)]

use std::io;

/// various log levels
#[derive(Clone, PartialEq, Eq, Debug)]

pub enum LogLevel {
    Info,
    Warning,
    Error,
}
/// primary function for emitting logs
pub fn log(level: LogLevel, message: &str) -> String {
    match level {
        LogLevel::Info => info(message),
        LogLevel::Error => error(message),
        LogLevel::Warning => warn(message),
    }
}
pub fn info(message: &str) -> String {
    "[INFO]: ".to_owned() + message
}
pub fn warn(message: &str) -> String {
    "[WARNING]: ".to_owned() + message
}
pub fn error(message: &str) -> String {
    "[ERROR]: ".to_owned() + message
}


// pub fn log(level: LogLevel, message: &str) -> String {
//     let level_message = match level {
//         LogLevel::Info => "[INFO]: ",
//         LogLevel::Error => "[ERROR]: ",
//         LogLevel::Warning => "[WARNING]: ",
//     };
//     level_message.to_owned() + message
// }
