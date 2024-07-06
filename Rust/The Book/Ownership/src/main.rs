#![allow(dead_code)]

fn main() {}

fn first_word_improved(s: &str) -> &str {
    let bytes = s.as_bytes();

    for (i, &item) in bytes.iter().enumerate() {
        if item == b' ' {
            return &s[..i];
        }
    }

    return &s[..];
}

fn first_word_1(s: &String) -> usize {
    let bytes = s.as_bytes();

    for (i, &item) in bytes.iter().enumerate() {
        if item == b' ' {
            return i;
        }
    }

    return s.len();
}

fn first_word_2(s: &String) -> &str {
    let bytes = s.as_bytes();

    for (i, &item) in bytes.iter().enumerate() {
        if item == b' ' {
            return &s[..i];
        }
    }

    return &s[..];
}

/*fn copy_and_move() {
    let v: Vec<String> = vec![String::from("Hello World")];
    let s_ref = &v[0];
    let s = *s_ref;
}
*/
fn remove_it_dammit() {
    let mut v = vec![String::from("Hello World")];
    let mut s = v.remove(0);
    s.push('!');
    println!("{s}");
    println!("{:?}", v);
}

fn copy_and_move_2() {
    let v = vec![String::from("Hello World")];
    let s_ref = &v[0];
    println!("{s_ref}");
}

fn copy_and_move_3() {
    let v = vec![String::from("Hello World"), String::from("from, Parth")];
    let mut s = v[0].clone();
    s.push('!');
    println!("{s}");
    println!("{:?}", v);
}

fn add_big_strings_1(dst: &mut Vec<String>, src: &[String]) {
    let largest: String = dst.iter()
        .max_by_key(|s| s.len())
        .unwrap()
        .clone();

    for s in src {
        if s.len() > largest.len() {
            dst.push(s.clone());
        }
    }
}

fn add_big_strings_2(dst: &mut Vec<String>, src: &[String]) {
    let largest: &String = dst.iter()
        .max_by_key(|s| s.len())
        .unwrap();

    let _to_add: Vec<String> = src.iter()
        .filter(|s| s.len() > largest.len())
        .cloned()
        .collect();
}

fn add_big_strings_3(dst: &mut Vec<String>, src: &[String]) {
    let largest: usize = dst.iter()
        .max_by_key(|s| s.len())
        .unwrap()
        .len();

    for s in src {
        if s.len() > largest {
            dst.push(s.clone());
        }
    }
}

/*fn _stringify_name_with_reference(name: &Vec<String>) -> String {
    name.push(String::from("Esq."));
    let full = name.join(" ");
    return full;
}
*/
fn _stringify_name_with_title_cloning(name: &Vec<String>) -> String {
    let mut name_clone = name.clone();
    name_clone.push(String::from("Esq."));
    let full = name_clone.join(" ");
    return full;
}

fn _stringify_name_with_title_later(name: &Vec<String>) -> String {
    let mut full = name.join(" ");
    full.push_str("Esq.");
    return full;
}

fn _calculate_length_with_reference(s: &String) -> usize {
    return s.len();
}

fn ascii_capitalize(v: &mut Vec<char>) {
    let c = &v[0];

    if c.is_ascii_lowercase() {
        let up = c.to_ascii_uppercase();
        v[0] = up;
    } else {
        println!("Already capitalized: {:?}", v);
    }
}

// Solutions
fn _return_a_string_2() -> String {
    let s = String::from("Hello World");
    return s;
}

fn _return_a_reference(output: &mut String) {
    output.replace_range(.., "Hello World");
}