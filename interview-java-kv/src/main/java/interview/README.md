# KV store with fuzzy timestamp search

Did an interview in 2020-10 and this is the solution.

I also created some files that improve on my original answer.

The challenge was to create a class that:
- Could store data as key-value
- Assigned a timestamp in milliseconds to each value
- Could get latest data when providing just key
- Could get specific timestamped value when providing both key and timestamp.
- If getting from key and timestamp and no exact timestamp exists, get next lower timestamp than the one provided.