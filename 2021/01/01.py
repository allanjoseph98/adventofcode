import pandas as pd

df = pd.read_csv(r'input.txt', names=['depths'])

print(sum(df['depths']>df['depths'].shift()))
rolled = df['depths'].rolling(3,center=True).sum()
print(sum(rolled>rolled.shift()))