import sys

def exec(initial, minBound, map):
    stepCount = 0
    current = initial
    while current >= minBound:
        if ((current & 1) == 0):
            current = current >> 1
        else:
            current = (current * 3) + 1
        stepCount += 1
    return stepCount + map[current]

max = int(sys.argv[1])
maxMap = min(10000000, int(max / 10))

total = 0
map = [0, 0]

for num in range(2, maxMap):
    res = exec(num, num, map)
    total += res
    map.append(res)

for num in range(maxMap, max):
    total += exec(num, maxMap, map)

print(total)
