# Duke Bot
The bot for the Duke Syndicate discord.

## Example docker compose
```yaml 
services:
  bot:
    build: .
    environment:
      - BOT_TOKEN=token_goes_here
```