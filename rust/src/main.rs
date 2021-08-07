use rayon::prelude::*;

const MAX: usize = 10_000_000;
const MAX_MAP: usize = {
    let res = MAX / 10;
    if res > 50_000_000 {
        50_000_000
    } else {
        res
    }
};

fn main() {
    let mut total = 0;
    let mut map = Vec::with_capacity(MAX_MAP + 4);
    map.push(0);
    map.push(0);

    for num in 2..MAX_MAP {
        let count = exec(num, num, &map);
        total += count;
        map.push(count);
    }

    total += ((MAX_MAP )..MAX)
        .into_par_iter()
        .map(|num| exec(num, MAX_MAP, &map))
        .sum::<usize>();

    println!("{}", total);
}

#[inline]
fn exec(initial: usize, min_bound: usize, map: &Vec<usize>) -> usize {
    let mut step_count = 0;
    let mut current = initial;
    while current >= min_bound {
        if (current & 1) == 0 {
            current /= 2;
        } else {
            current = (current * 3) + 1;
        }
        step_count += 1;
    }
    step_count + map[current]
}