function fn() {
  var env = karate.env; // get system property 'karate.env'
  karate.log('karate.env system property was:', env);
  if (!env) {
    env = 'local';
  }

  var config = {
    apiUrl: 'http://localhost:8080/'
  }

  if (env === 'local') {
    // customize
    apiUrl: 'http://localhost:8080/'
  } else if (env === 'dev') {
    // customize
    apiUrl: 'https://zq3ktf6gm3.execute-api.eu-central-1.amazonaws.com/dev/'
  } else if (env === 'qa') {
    // customize
  } else if (env === 'prod') {
    // customize
  }

  karate.configure('headers',
      {
        Authorization: 'AWS4-HMAC-SHA256 Credential=ffasfdsf',
        'X-Correlation-ID': '122'
      }
  )

  return config;
}
