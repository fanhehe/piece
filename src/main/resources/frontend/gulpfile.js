const gulp = require('gulp');
const clean = require('gulp-clean');
const babel = require('gulp-babel');

gulp.task('script', async () => {
    return await gulp
        .src(['./src/script/**/*.js'])
        .pipe(babel())
        .on('error',(e) => { console.log(e)})
        .pipe(gulp.dest('./dist/script'));
});

gulp.task('style', async () => {
    return await gulp
        .src(['./src/style/**/*.css'])
        .pipe(gulp.dest('./dist/style'));
});

gulp.task('clean', async () => {
    return await gulp
        .src(['./dist/style', './dist/script'], { read: false, allowEmpty: true })
        .pipe(clean());
});

gulp.task('dist', gulp.series(['clean', 'script', 'style'], async () => {}));
