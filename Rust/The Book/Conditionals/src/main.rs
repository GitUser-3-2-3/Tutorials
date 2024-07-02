fn main() {
    println!();

    for_loop();
}

fn if_else() {
    let n = 5;

    if n < 0 {
        print!("{n} is negative")
    } else if n > 0 {
        print!("{n} is positive")
    } else {
        print!("{n} is zero")
    }

    let big_n =
        if n < 10 && n > -10 {
            println!(", and is a small number, increase ten-fold");
            10 * n
        } else {
            println!(", and is a big number, halve the number");
            n / 2
        };

    println!("{} -> {}", n, big_n);
}

fn loop_demo() {
    let mut count = 0u32;
    println!("Let's count until infinity");

    // Infinite loop
    loop {
        count += 1;

        if count == 3 {
            println!("Three");
            continue;
        }

        println!("{count}");

        if count == 5 {
            println!("Okay! that's enough");
            break;
        }
    }
}

fn inner_outer() {
    'outer: loop {
        println!("Entered the outer loop");

        'inner: loop {
            println!("Entered the inner loop");
            //break; // inner loop broke

            //println!("Didn't reach statement");
            break 'outer;
        }

        println!("Never reached");
    }

    println!("Exited the outer loop");
}

fn return_value() {
    let mut counter = 0;

    let result = loop {
        counter += 1;

        if counter == 10 {
            break counter * 2;
        }
    };

    assert_eq!(result, 20);
}

fn fizz_buzz() {
    let mut n = 1;

    while n < 101 {
        if n % 15 == 0 {
            println!("FizzBuzz");
        } else if n % 3 == 0 {
            println!("Fizz");
        } else if n % 5 == 0 {
            println!("Buzz");
        } else {
            println!("{n}")
        }

        n += 1;
    }
}

fn iterator_one() {
    let names = vec!["Bob", "Frank", "Ferris"];

    for name in names.iter() {
        match name {
            &"Ferris" => println!("There is a crustacean"),
            _ => println!("Hello {name}")
        }
    }

    println!("names: {:?}", names);
}

fn loop_labels() {
    let mut count = 0;

    'counting_up: loop {
        println!("count: {count}");
        let mut remaining = 10;

        loop {
            println!("remaining: {remaining}");
            if remaining == 9 {
                break;
            }
            if count == 2 {
                break 'counting_up;
            }
            remaining -= 1;
        }
        count += 1;
    }
    println!("End count = {count}")
}

fn for_loop() {
    let a = [1, 2, 3, 4, 5];

    for element in a {
        println!("{element}")
    }

    for number in (1..5).rev() {
        println!("{number}");
    }

    print!("LIFTOFF!!!");
}