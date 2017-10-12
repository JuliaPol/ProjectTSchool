
var webpack = require('webpack');
var HtmlWebpackPlugin = require('html-webpack-plugin');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var helpers = require('./helpers');
//const { CheckerPlugin } = require('awesome-typescript-loader');
module.exports = {
  entry: {
    'app': './app/main.ts',
    //'polyfills': './app/polyfills.ts',
    'vendor': './app/vendor.ts'
  },
  resolve: {
    extensions: ['', '.ts', '.js']
  },
  module: {
    loaders: [
      {
        test: /\.ts$/,
        loaders: ['awesome-typescript-loader', 'angular2-template-loader']
      },
      {
        test: /\.html$/,
        loader: 'html'
      },
      {
        test: /\.(png|jpe?g|gif|svg|woff|woff2|ttf|eot|ico)$/,
        loader: 'file?name=assets/[name].[hash].[ext]'
      },
      {
        test: /\.css$/,
        exclude: helpers.root('app'),
        loader: ExtractTextPlugin.extract('style', 'css?sourceMap')
      },
      {
        test: /\.css$/,
        include: helpers.root('app'),
        loader: 'raw'
      }
    ]
  },
  plugins: [
    new webpack.optimize.CommonsChunkPlugin({
      name: ['app', 'polyfills', 'vendor']
    }),
    new HtmlWebpackPlugin({
      template: './index.html',
      baseUrl: '/'
    }),
    new CheckerPlugin()
  ]
};
