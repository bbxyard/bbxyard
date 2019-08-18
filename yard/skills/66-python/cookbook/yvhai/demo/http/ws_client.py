import websocket


def on_message(ws, msg):
    print(msg)


def on_error(ws, error):
    print(error)


def on_close(ws):
    print("connection closed ...")


def on_open(ws):
    req = '{"event": "subscribe", "channel":"chan1"}'
    print(req)
    ws.send(req)


if __name__ == '__main__':
    # websocket.enableTrace(True)
    ws = websocket.WebSocketApp("ws://127.0.0.1:8181",
                                on_message=on_message,
                                on_error=on_error,
                                on_close=on_close)
    ws.on_open = on_open
    ws.run_forever(ping_timeout=30)
