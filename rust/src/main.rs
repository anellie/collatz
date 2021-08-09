use rayon::prelude::*;
use std::env;

fn main() {
    let max = env::args().nth(1).unwrap().parse::<usize>().unwrap();
    let max_map = usize::min(50_000_000, max / 10);

    let mut total = 0;
    let mut map = Vec::with_capacity(max_map + 4);
    map.push(0);
    map.push(0);

    for num in 2..max_map {
        let count = exec(num, num, &map);
        total += count;
        map.push(count);
    }

    total += ((max_map)..=max)
        .into_par_iter()
        .map(|num| exec(num, max_map, &map))
        .sum::<usize>();

    println!("{}", total);
}

#[inline]
fn exec(initial: usize, min_bound: usize, map: &[usize]) -> usize {
    let mut step_count = 0;
    let mut current = initial;

    if (current & 1) == 0 {
        let trailing = current.trailing_zeros();
        step_count += trailing as usize;
        current >>= trailing;
    }

    while current >= min_bound {
        if (current & 1) == 0 {
            current /= 2;
            step_count += 1;
        } else {
            current = ((current * 3) + 1) / 2;
            step_count += 2;
        }
    }

    step_count + map[current]
}
