struct User {
    active: bool,
    username: String,
    password: String,
    email: String,
    sign_in_count: u64,
}

struct Color(i32, i32, i32);
struct Point(i32, i32, i32);
struct Coin {
    x: i32,
    y: i32,
}

#[derive(Debug)]
struct Rectangle {
    width: u32,
    height: u32,
}

impl Rectangle {
    fn can_hold(&self, other: &Rectangle) -> bool {
        return self.width > other.width && self.height > other.height;
    }

    fn square(size: u32) -> Self {
        Self {
            width: size,
            height: size,
        }
    }
}

impl Rectangle {
    fn area(&self) -> u32 {
        return self.width * self.height;
    }

    fn set_width(&mut self, width: u32) {
        self.width = width;
    }

    fn max(self, other: Rectangle) -> Rectangle {
        Rectangle {
            width: self.width.max(other.width),
            height: self.height.max(other.height),
        }
    }
}

fn main() {
    println!();

    let r = Rectangle {
        width: 0,
        height: 0,
    };
}

fn area_struct(rectangle: &Rectangle) -> u32 {
    return rectangle.width * rectangle.height;
}

fn area(width1: u32, height1: u32) -> u32 {
    return width1 * height1;
}

fn build_user(username: String, password: String, email: String) -> User {
    User {
        active: true,
        username,
        password,
        email,
        sign_in_count: 1,
    }
}

