// importa nas paginas sempre que for usar o scss
// import '../styles/styles.scss';

const path = require('path');

module.exports = {
  webpack: (config, options) => {
    config.module.rules.push({
      test: /\.scss$/,
      use: [
        options.defaultLoaders.babel,
        {
          loader: require('sass-loader'),
          options: {
            implementation: require('sass'),
          },
        },
      ],
      include: path.resolve(__dirname, '../'),
    });

    return config;
  },
};

