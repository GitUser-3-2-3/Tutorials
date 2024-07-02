use std::io;

fn main() {
   let x: i32 = plus_one(5);
   println!("value of x is {x}")
}

fn plus_one(x: i32) -> i32 {
   return x + 1;
}

fn data_types() {
   let heart_eyed_cat = '😻';
   println!("{heart_eyed_cat}")
}

fn tuples() {
   let tup = (50, 2.3, 1);

   let (x, y, z) = tup;
   println!("The value of y is: {y}");

   let five_hundred = tup.0;
   let two_point_three = tup.1;
   let one = tup.2;

   println!("{five_hundred}, {two_point_three}, {one}")
}

fn array() {
   let a = [1, 3, 4, 5, 8, 3];
   let a = [3; 5];
}

fn get_index() {
   let a = [1, 2, 3, 4, 5];

   println!("enter an array index.");
   let mut index = String::new();

   io::stdin()
      .read_line(&mut index)
      .expect("Failed to read line");

   let index: usize = index
      .trim().parse()
      .expect("Index entered was not a number");

   let element = a[index];
   println!("The value of element at {index} is : {element}")
}