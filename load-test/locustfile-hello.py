from locust import task, FastHttpUser, stats

stats.PERCENTILES_TO_CHART = [0.50, 0.99] #특정 Percentile 차트 보도록 수정

class HelloWorld(FastHttpUser):
    connection_timeout = 10.0
    network_timeout = 10.0

    @task
    def hello(self):
        response = self.client.get("/hello")